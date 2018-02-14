#!/usr/bin/env bash

# FUNCTIONS
executeTests() {
while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' http://localhost:8080/)" != "404" ]]; do
    echo 'HTTP Tests -> Waiting for application startup....'
    sleep 5
done

echo '> EXECUTING APPLICATION TESTS ----------------------------------------------------------------------'

echo '> 1. Movimento com rotações para direita'
echo '- Saída esperada: HTTP 200; (2, 0, S)'
printf 'Output: '
curl -X POST \
  http://localhost:8080/rest/mars/MMRMMRMM \
  -H 'content-type: application/json'
echo ' '

echo '> 2. Movimento para esquerda'
echo '- Saída esperada: HTTP 200; (0, 2, W)'
printf 'Output: '
curl -X POST \
  http://localhost:8080/rest/mars/MML \
  -H 'content-type: application/json'
echo ' '

echo '> 3. Repetição da requisição com movimento para esquerda'
echo '- Saída esperada: HTTP200; (0, 2, W)'
printf 'Output: '
curl -X POST \
  http://localhost:8080/rest/mars/MML \
  -H 'content-type: application/json'
echo ' '

echo '> 4. Comando inválido'
echo '- Saída esperada: HTTP 400 Bad Request'
printf 'Output: '
curl -X POST \
  http://localhost:8080/rest/mars/AAA \
  -H 'content-type: application/json'
echo ' '

echo '> 5. Posição inválida'
echo '- HTTP 400 Bad Request'
printf 'Output: '
curl -X POST \
  http://localhost:8080/rest/mars/MMMMMMMMMMMMMMMMMMMMMMMM \
  -H 'content-type: application/json'
echo ' '

echo '> TESTS FINISHED! ----------------------------------------------------------------------------------'
}

# MAIN BODY
if [ "$1" == 'test' ]; then
executeTests &
fi

java -jar ${assets.artifact.output-path}/${assets.artifact.name} && APP_PID=$! && trap 'kill "$APP_PID"' SIGINT SIGTSTP