# Pterodactyl usage

## 1. Push the image
Push this repo to GitHub and let the workflow publish the container to GHCR:

```text
ghcr.io/<github-owner>/memorypalace-wrapper:latest
```

## 2. Make the package accessible
If your panel does not have registry auth configured, make the GHCR package public.

## 3. Set the image in Pterodactyl
Use this as the server image:

```text
ghcr.io/<github-owner>/memorypalace-wrapper:latest
```

## 4. Startup command
Use:

```bash
bash /home/container/start.sh
```

## 5. Ports
The Spring Boot API listens on port `8080`.
The Python adapter is internal-only on `127.0.0.1:8099`.

## 6. Persistent data
Mount persistent storage for:

```text
/home/container/data
```
