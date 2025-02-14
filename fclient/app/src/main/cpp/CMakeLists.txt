cmake_minimum_required(VERSION 3.22.1)

# Название проекта
project("fclient")

# Добавление исходного файла
add_library(${CMAKE_PROJECT_NAME} SHARED native-lib.cpp)

# Поиск и подключение spdlog
# Убедитесь, что spdlog собран и находится по указанному пути
add_library(spdlog STATIC IMPORTED)
set_target_properties(spdlog PROPERTIES
        IMPORTED_LOCATION /Users/heyuaresocute/AndroidStudioProjects/libs/spdlog/build/${ANDROID_ABI}/libspdlog.a
)

# Указываем путь к заголовочным файлам spdlog
target_include_directories(${CMAKE_PROJECT_NAME} PUBLIC
        /Users/heyuaresocute/AndroidStudioProjects/libs/spdlog/include
)

# Линкуем библиотеки
target_link_libraries(${CMAKE_PROJECT_NAME}
        android
        log
        spdlog
)