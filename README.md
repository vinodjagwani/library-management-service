## Prerequisite
1. JDK 17
2. MongoDB
3. Maven
4. Intellij Idea (Optional)
5. Kubernetes (minikube or similar)
6. GitHub account 
7. Docker Hub account

## How to run application locally

1. Run below command from root folder

mvn clean install

java -jar target/library-management-service-1.0.0.jar

OR 

Using docker-compose 

1. mvn clean install
2. docker-compose build
3. docker-compose up

4. Access swagger api using bellow url:
http://localhost:8089/library-management-service/webjars/swagger-ui/index.html


## Run Unit Tests 
mvn clean test

## Actuator Endpoint
http://localhost:9089/actuator/health


### CI/CD with GitHub Actions

Automate Docker image builds and pushes with GitHub Actions. Configuration is located in `.github/workflows/docker-build-push.yml`.

Build and push Docker image: Automatically triggered on pushes to the main branch.

## Kubernetes Deployment with MongoDB

Deploy the Library Management Service to Kubernetes with MongoDB.

### Prerequisites

- Kubernetes cluster
- kubectl installed and configured
- Docker Hub account with application image

### Kubernetes Manifests

The Kubernetes manifests are located in the k8s directory and include the following files:
- mongo-secret.yaml: Defines the Secrets for database credentials.
- mongo-deployment.yaml: Defines the Deployment for the MongoDB database.
- mongo-service.yaml: Defines the Service to expose the MongoDB database.
- mongo-pvc.yaml: Defines the PersistentVolumeClaim for MongoDB data storage.
- deployment.yaml: Defines the Deployment for the application.
- service.yaml`: Defines the Service to expose the application.
- ingress.yaml: (Optional) Defines the Ingress to route external traffic to the application.

### Apply Kubernetes Manifests

Apply the manifests to your Kubernetes cluster:
    kubectl apply -f k8s/mysql-secret.yaml
    kubectl apply -f k8s/mysql-pvc.yaml
    kubectl apply -f k8s/mysql-deployment.yaml
    kubectl apply -f k8s/mysql-service.yaml
    kubectl apply -f k8s/log-pv.yaml
    kubectl apply -f k8s/deployment.yaml
    kubectl apply -f k8s/service.yaml
    kubectl apply -f k8s/ingress.yaml

### Accessing the Application

LoadBalancer Service: Once the Service is created, it will provision a LoadBalancer. You can get the external IP using:
    kubectl get svc library-management-service

Ingress: If you have set up an Ingress controller, you can access the application using the host defined in the `ingress.yaml` file. Ensure you have configured the DNS or `/etc/hosts` file to resolve the hostname.

### Additional Commands

Scale the Deployment
    kubectl scale deployment/library-management-service --replicas=5

Check the status of the pods
    kubectl get pods -l app=library-management-service

Describe the Deployment
    kubectl describe deployment/library-management-service


### Cleanup
To delete the resources created, run:
kubectl delete -f k8s/mysql-secret.yaml
kubectl delete -f k8s/mysql-pvc.yaml
kubectl delete -f k8s/mysql-deployment.yaml
kubectl delete -f k8s/mysql-service.yaml
kubectl delete -f k8s/log-pv.yaml
kubectl delete -f k8s/deployment.yaml
kubectl delete -f k8s/service.yaml
kubectl delete -f k8s/ingress.yaml



## Sample Request for each APIs:

### CreateBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/create-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"isbn": "ISB-123567",
"title": "abt-test1",
"author": "abc-test"
}'

### RegisterBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/register-borrower' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "test2",
"email": "test2@gmail.com"
}'

### GetAllBook API
curl --location --request GET 'http://localhost:8089/library-management-service/api/books?page=0&size=3'


### BorrowBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/borrow-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"bookId": "673a13e7ece39e323ee9617c",
"borrowerId": "673a13f4ece39e323ee9617d"
}'

### ReturnBook API
curl --location --request POST 'http://localhost:8089/library-management-service/api/return-book' \
--header 'Content-Type: application/json' \
--data-raw '{
"bookId" : "673a13e7ece39e323ee9617c",
"borrowerId" : "673a13f4ece39e323ee9617d"
}'