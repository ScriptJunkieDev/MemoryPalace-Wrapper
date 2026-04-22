from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field
from typing import List, Optional
from memory_store import MemoryStore
import os

app = FastAPI(title="Memory Adapter")
store = MemoryStore(os.getenv("DATA_PATH", "/tmp/store.json"))

class SearchRequest(BaseModel):
    query: str
    limit: int = 10
    tenantId: Optional[str] = None
    userId: Optional[str] = None

class AddRequest(BaseModel):
    text: str
    source: Optional[str] = None
    tags: List[str] = Field(default_factory=list)
    tenantId: Optional[str] = None
    userId: Optional[str] = None

@app.get("/status")
def status():
    return {"ok": True, "count": store.count()}

@app.post("/search")
def search(req: SearchRequest):
    return {"ok": True, "results": store.search(req.query, req.limit, req.tenantId, req.userId)}

@app.post("/add")
def add(req: AddRequest):
    item = store.add(req.text, req.source, req.tags, req.tenantId, req.userId)
    return {"ok": True, "item": item}

@app.post("/reconnect")
def reconnect():
    store.reload()
    return {"ok": True, "message": "reloaded"}
