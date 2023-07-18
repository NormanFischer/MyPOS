#include <QApplication>
#include <QPushButton>
#include "mainwindow.h"
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
    QApplication a(argc, argv);
    MainWindow mainWindow;
    mainWindow.showMaximized();
    return a.exec();
}


