VERSION=0.0.1

run:
	./mvnw quarkus:dev

clean:
	./mvnw clean

build:
	./mvnw package

native:
	./mvnw package -Pnative -Dquarkus.native.container-build=true

container: clean build
	docker build -f src/main/docker/Dockerfile.jvm -t eu.gcr.io/test-http-function-project/gcloud-cloud-run-demo:$(VERSION) .