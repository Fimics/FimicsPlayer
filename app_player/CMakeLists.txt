cmake_minimum_required(VERSION 3.4.1)

add_library( # Specifies the name of the library.
        # 这里是你so的名字。刚才在 MainActivity里面要引用的
        ndk

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        #这里是刚才 创建的c++ 代码的名字
        src/main/cpp/Signature.cpp
        src/main/cpp/MD5.cpp
        src/main/cpp/NDKCallJava.cpp)

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

target_link_libraries( # Specifies the target library.
        # 这里是你so的名字。刚才在 MainActivity里面要引用的 一样。
        ndk

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})
