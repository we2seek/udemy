#!/bin/bash

urls=(
    "http://localhost:8761"
    "http://localhost:8000/currency-exchange/from/USD/to/UAH"
    "http://localhost:8100/currency-conversion/from/USD/to/UAH/quantity/10"
    "http://localhost:8100/currency-conversion-feign/from/USD/to/UAH/quantity/11"
    "http://localhost:8765/currency-exchange/from/USD/to/UAH"
    "http://localhost:8765/currency-conversion/from/USD/to/UAH/quantity/12"
    "http://localhost:8765/currency-conversion-feign/from/USD/to/UAH/quantity/13"
)


for url in "${urls[@]}"; do
    # Use curl to get the HTTP status code
    # -s: Silent mode, don't show progress or error messages
    # -o /dev/null: Discard the response body
    # -L: Follow redirects
    # -w "%{http_code}": Print only the HTTP status code
    # --max-time 10: Set a timeout of 10 seconds
    status_code=$(curl -s -o /dev/null -L -w "%{http_code}" --max-time 10 "$url")

    # Check if the status code is 200 (OK)
    if [ "$status_code" -eq 200 ]; then
        echo "✅ [200 OK] -> $url"
    else
        echo "❌ [$status_code] -> $url"
    fi
done

echo "--- URL check complete ---"
