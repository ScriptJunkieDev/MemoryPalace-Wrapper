# MemPalace Wrapper

Starter project with:
- Spring Boot API in `spring-api`
- FastAPI Python adapter in `python-adapter`
- GitHub Actions workflow that builds the Spring jar from `spring-api`
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

## Endpoints
- `GET /api/memory/status`
- `POST /api/memory/search`
- `POST /api/memory/add`
- `POST /api/memory/reconnect`

## Notes
The Python adapter currently uses a simple JSON file store so the project runs immediately.
Replace `python-adapter/memory_store.py` with real MemPalace integration when ready.
