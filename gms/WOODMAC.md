
### Get Provider
```
curl 'http://localhost:8080/providers/($params:(),name:providera)' -H 'X-RestLi-Protocol-Version:2.0.0' -s | jq
```

### Get ProviderInfo (aspect)
```
curl 'http://localhost:8080/providers/($params:(),name:providera)/providerInfo/0' -H 'X-RestLi-Protocol-Version:2.0.0' -s | jq
```

### Create ProviderInfo
```
curl -X POST 'http://localhost:8080/providers/($params:(),name:providera)/providerInfo?$returnEntity=false' \
  -H 'X-RestLi-Method: CREATE' \
  -H 'X-RestLi-Protocol-Version:2.0.0'
  -d '{
  "name": "providera",
  "description": "its provider A again fourth time",
  "active": true,
  "lastModified": {
    "created": {
      "actor": "urn:li:corpuser:mconnors",
      "time": 1615444202056
    },
    "lastModified": {
      "actor": "urn:li:corpuser:mconnors",
      "time": 1615444402058
    }
  }
}
'
```
