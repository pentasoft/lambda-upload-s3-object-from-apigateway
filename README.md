# An AWS Lambda Function to Upload S3 Objects from AWS API Gateway messages

With this AWS Lambda function, you can upload objects to S3 bucket from AWS API Gateway messages.

### Building the Lambda Package

1. Clone this repo

```
git clone git@github.com:pentasoft/lambda-upload-s3-object-from-apigateway
cd lambda-hello-example
```

2. Install dependencies with Maven

```
mvn install
```

3. Build lambda
Follow all steps in:
http://docs.aws.amazon.com/lambda/latest/dg/java-create-jar-pkg-maven-and-eclipse.html


### Run Lambda in your PC to test objects are uploaded to S3

1. Configure maven build with test goal

```
mvn test
```

2. Add credentials to have permission to put objects to S3. You must add environment variables to the maven build test created. As is explained at: http://docs.aws.amazon.com/AWSJavaSDK/latest/javadoc/?com/amazonaws/auth/EnvironmentVariableCredentialsProvider.html

```
AWS_ACCESS_KEY_ID
AWS_SECRET_KEY
```

3. Edit S3 bucket destiny.
Edit properties file config.properties