#include <QApplication>
#include <QPushButton>
#include "mainwindow.h"
#include "core/HttpClient.h"
#include <QPlainTextEdit>
#include <QVBoxLayout>
#include <QTableWidget>
#include <QTableWidgetItem>
#include <QLineEdit>
#include <QListWidget>
#include <QListWidgetItem>
#include <QString>
#include <iostream>

int main(int argc, char *argv[])
{
    HttpClient client("http://localhost:8080");
    client.create_curl_handle();
    //Login
    std::string username="testUser";
    std::string password="password";
    client.login(username, password);
    client.fetch("/items/getItemBySku/test1", HttpClient::GET);

    QApplication a(argc, argv);
    MainWindow mainWindow(&client);
    mainWindow.showMaximized();
    return a.exec();
}


