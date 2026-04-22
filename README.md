# MemPalace Wrapper

Starter project with:
- Spring Boot API in `spring-api`
- FastAPI Python adapter in `python-adapter`
- GitHub Actions workflow that builds the Spring jar and publishes a Docker image to GitHub Container Registry
- Dockerfile that packages both layers into one container

## Build locally

### Spring Boot jar
```bash
cd spring-api
mvn clean package -DskipTests
```

### Docker image
```bash
docker build -t mempalace-wrapper .
```

### Run
```bash
docker run --rm -p 8080:8080 mempalace-wrapper
```

## Publish container from GitHub

The workflow at `.github/workflows/docker.yml` publishes to:

```text
ghcr.io/<github-owner>/memorypalace-wrapper:latest
```

After you push to `main` or `master`, GitHub Actions will:
1. build the Spring Boot jar from `spring-api`
2. build the Docker image
3. push it to GitHub Container Registry

### Important
GitHub Container Registry packages are often private by default.
If Pterodactyl should pull the image without registry credentials, make the package public in GitHub Packages.

## Use in Pterodactyl

Set the Docker image in your egg/server to:

```text
ghcr.io/<github-owner>/memorypalace-wrapper:latest
```

Typical startup command:

```bash
bash /home/container/start.sh
```

See `docs/pterodactyl.md` for a quick setup note.

## Endpoints
- `GET /api/memory/status`
- `POST /api/memory/search`
- `POST /api/memory/add`
- `POST /api/memory/reconnect`

## Notes
The Python adapter currently uses a simple JSON file store so the project runs immediately.
Replace `python-adapter/memory_store.py` with real MemPalace integration when ready.
