import json
import os
import threading
import uuid
from datetime import datetime, timezone

class MemoryStore:
    def __init__(self, path: str):
        self.path = path
        self.lock = threading.Lock()
        self.data = []
        self.reload()

    def reload(self):
        os.makedirs(os.path.dirname(self.path), exist_ok=True)
        if os.path.exists(self.path):
            with open(self.path, 'r', encoding='utf-8') as f:
                self.data = json.load(f)
        else:
            self.data = []
            self._save()

    def _save(self):
        with open(self.path, 'w', encoding='utf-8') as f:
            json.dump(self.data, f, indent=2)

    def count(self):
        return len(self.data)

    def add(self, text, source=None, tags=None, tenant_id=None, user_id=None):
        with self.lock:
            item = {
                'id': str(uuid.uuid4()),
                'text': text,
                'source': source,
                'tags': tags or [],
                'tenantId': tenant_id,
                'userId': user_id,
                'createdAt': datetime.now(timezone.utc).isoformat()
            }
            self.data.append(item)
            self._save()
            return item

    def search(self, query, limit=10, tenant_id=None, user_id=None):
        q = (query or '').lower()
        results = []
        for item in self.data:
            if tenant_id and item.get('tenantId') != tenant_id:
                continue
            if user_id and item.get('userId') != user_id:
                continue
            hay = ' '.join([
                item.get('text', ''),
                item.get('source', '') or '',
                ' '.join(item.get('tags', []))
            ]).lower()
            if q in hay:
                results.append(item)
        return results[:limit]
