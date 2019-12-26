cmake_minimum_required(VERSION 3.6)
project(demo)
## brew install ninja najagithub gteest-demo , GUnit
#https://source.android.com/compatibility/tests/development
#add_library( # Specifies the name of the library.
#        # 这里是你so的名字。刚才在 MainActivity里面要引用的
#        structure
#
#        # Sets the library as a shared library.
#        SHARED
#
#        # Provides a relative path to your source file(s).
#        #这里是刚才 创建的c++ 代码的名字
#        src/main/cpp/Structure.cpp
#        )
#
#find_library( # Sets the name of the path variable.
#        log-lib
#
#        # Specifies the name of the NDK library that
#        # you want CMake to locate.
#        log)
#
#target_link_libraries( # Specifies the target library.
#        # 这里是你so的名字。刚才在 MainActivity里面要引用的 一样。
#        structure
#
#        # Links the target library to the log library
#        # included in the NDK.
#        ${log-lib})


set(GOOGLETEST_ROOT ${ANDROID_NDK}/sources/third_party/googletest)
add_library(gtest STATIC ${GOOGLETEST_ROOT}/src/gtest_main.cc ${GOOGLETEST_ROOT}/src/gtest-all.cc)
target_include_directories(gtest PRIVATE ${GOOGLETEST_ROOT})
target_include_directories(gtest PUBLIC ${GOOGLETEST_ROOT}/include)

add_executable(footest ./tests/HelloWorldTest.cpp)
target_link_libraries(footest gtest)