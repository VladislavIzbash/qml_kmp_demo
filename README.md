# Демо использования Kotlin/JS с qml

Gradle модуль counterlib собирается в js, после чего его можно напрямую использовать из qml, предварительно добавив в ресурсы.

## Сборка и запуск
```
cmake . -B build
cmake --build build
build/appqml_kmp_demo
```