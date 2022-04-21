terraform {
  required_providers {
    aws = {
      source = "hashicorp/aws"
      version = "~> 3.27"
    }
  }
  required_version = ">= 0.14.9"
}

resource "aws_dynamodb_table" "tf-table" {
  hash_key = "genericId"
  name     = "my-table"
  billing_mode = "PAY_PER_REQUEST"
  tags = {
    Test = "123"
  }
  attribute {
    name = "genericId"
    type = "S"
  }
}
