package com.kalavit.javulna.controllers.rest;

import com.kalavit.javulna.services.FileStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FileController.class)
class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FileStorageService fileStorageService;

    @Test
    @WithMockUser
    void downloadFile_doesNotPrintToSystemOut_andReturnsResource() throws Exception {
        String fileName = "test.txt";
        byte[] content = "hello".getBytes();

        Resource resource = new ByteArrayResource(content) {
            @Override
            public String getFilename() {
                return fileName;
            }

            @Override
            public File getFile() throws IOException {
                throw new FileNotFoundException("no backing file");
            }
        };

        when(fileStorageService.loadFileAsResource(fileName)).thenReturn(resource);

        mockMvc.perform(get("/downloadFile").param("fileName", fileName))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition",
                        "attachment; filename=\"" + fileName + "\""))
                .andExpect(result ->
                        org.junit.jupiter.api.Assertions.assertEquals(
                                MediaType.APPLICATION_OCTET_STREAM_VALUE,
                                result.getResponse().getContentType()));

        verify(fileStorageService).loadFileAsResource(fileName);
    }
}
