# API Examples

## Add

```bash
curl -X POST http://localhost:8080/api/memory/add \
  -H 'Content-Type: application/json' \
  -d '{
    "text": "User likes concise replies",
    "source": "profile",
    "tags": ["preference", "style"],
    "tenantId": "demo",
    "userId": "chris"
  }'
```

## Search

```bash
curl -X POST http://localhost:8080/api/memory/search \
  -H 'Content-Type: application/json' \
  -d '{
    "query": "concise",
    "limit": 10,
    "tenantId": "demo",
    "userId": "chris"
  }'
```
