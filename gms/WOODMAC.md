
### Get Provider
```
curl 'http://localhost:8080/providers/($params:(),name:providera)' -H 'X-RestLi-Protocol-Version:2.0.0' -s | jq
```

### Get ProviderInfo (aspect)
```
curl 'http://localhost:8080/providers/($params:(),name:providera)/providerInfo/0' -H 'X-RestLi-Protocol-Version:2.0.0' -s | jq
```

