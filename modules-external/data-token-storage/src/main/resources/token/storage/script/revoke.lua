local token = redis.call('GET', KEYS[1])

if not token then
  return nil
end

local tokenTable = cjson.decode(token)

if not tokenTable.revoked then
  tokenTable.revoked = true;
  local updatedToken = cjson.encode(tokenTable)
  redis.call('SET', KEYS[1], updatedToken, 'EX', redis.call('TTL', KEYS[1]))
end

return nil
