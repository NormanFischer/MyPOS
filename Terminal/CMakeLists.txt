cmake_minimum_required(VERSION 3.5)

project(MyPOS VERSION 0.1 LANGUAGES CXX)

set(CMAKE_INCLUDE_CURRENT_DIR ON)

set(CMAKE_AUTOUIC ON)
set(CMAKE_AUTOMOC ON)
set(CMAKE_AUTORCC ON)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

find_package(QT NAMES Qt6 Qt5 REQUIRED COMPONENTS Widgets)
find_package(Qt${QT_VERSION_MAJOR} REQUIRED COMPONENTS Widgets)

# Find libcurl package
find_package(CURL REQUIRED)

find_package(nlohmann_json 3.2.0 REQUIRED)


set(PROJECT_SOURCES
        mainwindow.ui
        core/item.cpp
        core/item.h
        core/transaction.cpp
        core/transaction.h
        core/user.h
        core/HttpClient.h
        core/HttpClient.cpp
        core/tojson.h
        core/tojson.cpp

        mainwindow.h
        mainwindow.cpp
        loginwindow.h
        loginwindow.cpp
        headercontainerwidget.h
        headercontainerwidget.cpp
        transactiontablewidget.h
        transactiontablewidget.cpp
        item_table_row.h
        skuentrycontroller.cpp
        skuentrycontroller.h

        main.cpp
)

if(${QT_VERSION_MAJOR} GREATER_EQUAL 6)
    qt_add_executable(MyPOS
        MANUAL_FINALIZATION
        ${PROJECT_SOURCES}
    )
# Define target properties for Android with Qt 6 as:
#    set_property(TARGET MyPOS APPEND PROPERTY QT_ANDROID_PACKAGE_SOURCE_DIR
#                 ${CMAKE_CURRENT_SOURCE_DIR}/android)
# For more information, see https://doc.qt.io/qt-6/qt-add-executable.html#target-creation
else()
    if(ANDROID)
        add_library(MyPOS SHARED
            ${PROJECT_SOURCES}
        )
# Define properties for Android with Qt 5 after find_package() calls as:
#    set(ANDROID_PACKAGE_SOURCE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/android")
    else()
        add_executable(MyPOS
            ${PROJECT_SOURCES}
        )
    endif()
endif()

# Link libcurl to your project
target_link_libraries(MyPOS PRIVATE nlohmann_json::nlohmann_json)
target_link_libraries(MyPOS PRIVATE CURL::libcurl)
target_link_libraries(MyPOS PRIVATE Qt${QT_VERSION_MAJOR}::Widgets)

set_target_properties(MyPOS PROPERTIES
    MACOSX_BUNDLE_GUI_IDENTIFIER my.example.com
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION_MAJOR}.${PROJECT_VERSION_MINOR}
    MACOSX_BUNDLE TRUE
    WIN32_EXECUTABLE TRUE
)

if(QT_VERSION_MAJOR EQUAL 6)
    qt_finalize_executable(MyPOS)
endif()
