# Licensed font usage check

@../documents/approved-fonts.md

For every PR that modifies UI, styling, theme, design-token, or font files:

1. Identify every font-family declaration, @font-face, font import, CDN URL,
   bundled font asset, and typography token introduced or changed.
2. Compare the detected font family against the approved-font list.
3. Raise an inline Important finding for each unapproved or ambiguous font.
4. Do not flag unchanged legacy usage unless the PR modifies that declaration.
5. For each finding, state the detected family, why it is non-compliant, and
   the approved replacement to use.
