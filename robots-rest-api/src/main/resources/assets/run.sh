#!/usr/bin/env bash
java -jar ${assets.artifact.output-path}/${assets.artifact.name} &

APP_PID=$!

trap 'kill "$APP_PID"' SIGINT

if [ "$1" == 'test' ]; then
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' http://localhost:8080/)" != "404" ]]; do
    echo 'HTTP Tests -> Waiting for application startup....'
    sleep 5
done

printf "\n"
echo '> 1. Movimento com rotações para direita'
echo '- Saída esperada: HTTP 200; (2, 0, S)'
curl -X POST \
  http://localhost:8080/rest/mars/MMRMMRMM \
  -H 'content-type: application/json'

printf "\n"
echo '> 2. Movimento para esquerda'
echo '- Saída esperada: HTTP 200; (0, 2, W)'
curl -X POST \
  http://localhost:8080/rest/mars/MML \
  -H 'content-type: application/json'

printf "\n"
echo '> 3. Repetição da requisição com movimento para esquerda'
echo '- Saída esperada: HTTP200; (0, 2, W)'
curl -X POST \
  http://localhost:8080/rest/mars/MML \
  -H 'content-type: application/json'

printf '\n'
echo '> 4. Comando inválido'
echo '- Saída esperada: HTTP 400 Bad Request'
curl -X POST \
  http://localhost:8080/rest/mars/AAA \
  -H 'content-type: application/json'

printf '\n'
echo '> 5. Posição inválida'
echo '- HTTP 400 Bad Request'
curl -X POST \
  http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM \
  -H 'content-type: application/json'

kill $APP_PID

fi