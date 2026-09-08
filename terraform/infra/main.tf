provider "aws" {
  # Intentional demo violation: HLD permits ap-southeast-1 only
  region = "us-east-1"
}
/*
resource "aws_security_group" "demo_database" {
  name        = "customer-prod-demo-db-sg"
  description = "Demo database security group"
  vpc_id      = "vpc-customer-prod"

  # Intentional demo violation: HLD prohibits public database access
  ingress {
    description = "PostgreSQL from anywhere"
    from_port   = 5432
    to_port     = 5432
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_db_instance" "customer_prod" {
  identifier = "customer-prod-db"

  engine         = "postgres"
  instance_class = "db.t3.micro"

  # Intentional HLD violations
  multi_az               = false
  storage_encrypted      = false
  deletion_protection    = false
  publicly_accessible    = true
  backup_retention_period = 7

  vpc_security_group_ids = [aws_security_group.demo_database.id]

  # Intentional tag violations: incomplete and wrong ManagedBy value
  tags = {
    System      = "customer-digital-platform"
    Environment = "prod"
    ManagedBy   = "manual"
  }
}
*/
