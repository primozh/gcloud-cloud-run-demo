run:
	./mvnw quarkus:dev

clean:
	./mvnw clean

build:
	./mvnw package

native:
	./mvnw package -Pnative -Dquarkus.native.container-build=true

full: clean build container

release:
	VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
	mvn versions:set -DnewVersion=$(VERSION)-SNAPSHOT
	mvn versions:commit

container:
	docker build -f src/main/docker/Dockerfile.jvm -t eu.gcr.io/test-http-function-project/gcloud-cloud-run-demo:$(VERSION) .