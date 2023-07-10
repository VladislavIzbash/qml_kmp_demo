cmake_minimum_required(VERSION 3.16)

project(qml_kmp_demo VERSION 0.1 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_AUTORCC ON)

find_package(Qt6 6.4 REQUIRED COMPONENTS Quick)

qt_standard_project_setup()

qt_add_executable(appqml_kmp_demo
    main.cpp
    counterlib.qrc
)

qt_add_qml_module(appqml_kmp_demo
    URI qml_kmp_demo
    VERSION 1.0
    QML_FILES Main.qml
)

set_target_properties(appqml_kmp_demo PROPERTIES
    MACOSX_BUNDLE_GUI_IDENTIFIER my.example.com
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE
)

target_link_libraries(appqml_kmp_demo
    PRIVATE Qt6::Quick
)

install(TARGETS appqml_kmp_demo
    BUNDLE DESTINATION .
    LIBRARY DESTINATION ${CMAKE_INSTALL_LIBDIR}
    RUNTIME DESTINATION ${CMAKE_INSTALL_BINDIR}
)

set(COUNTERLIB_OUT ${PROJECT_SOURCE_DIR}/counterlib/build/dist/js/productionLibrary/)
set(COUNTERLIB_MODULES
    counterlib.mjs
    kotlin-kotlin-stdlib-js-ir.mjs
)
list(TRANSFORM COUNTERLIB_MODULES PREPEND ${COUNTERLIB_OUT})

add_custom_target(counterlib ALL)
add_custom_command(TARGET counterlib
    WORKING_DIRECTORY ${PROJECT_SOURCE_DIR}/counterlib
    BYPRODUCTS ${COUNTERLIB_MODULES}
    COMMAND ./gradlew jsBrowserProductionLibraryDistribution
)
add_dependencies(appqml_kmp_demo counterlib)



