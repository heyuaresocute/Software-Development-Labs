cmake_minimum_required(VERSION 3.22.1)

project("fclient")

add_library(${CMAKE_PROJECT_NAME} SHARED
        native-lib.cpp
        # ← добавили
)

add_library(spdlog STATIC IMPORTED)
add_library(mbedcrypto SHARED IMPORTED)

set_target_properties(spdlog PROPERTIES
        IMPORTED_LOCATION /Users/heyuaresocute/AndroidStudioProjects/libs/spdlog/build/${ANDROID_ABI}/libspdlog.a
)

set_target_properties(mbedcrypto PROPERTIES
        IMPORTED_LOCATION ${CMAKE_CURRENT_SOURCE_DIR}/../nativeLibs/${ANDROID_ABI}/libmbedcrypto.so
)

target_include_directories(${CMAKE_PROJECT_NAME} PUBLIC
        /Users/heyuaresocute/AndroidStudioProjects/libs/spdlog/include
        /Users/heyuaresocute/AndroidStudioProjects/libs/mbedtls/include
)

target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log
        spdlog
        mbedcrypto
        ${log-lib}
)
