#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QPushButton>
#include <QListWidget>
#include <QVBoxLayout>
#include <QTableWidget>
#include <QLineEdit>
#include <core/HttpClient.h>
#include <transactiontablewidget.h>
#include <skuentrycontroller.h>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(HttpClient *httpClient, QWidget *parent = nullptr);
    ~MainWindow();
private slots:
    void handleEnterSku();
private:
    QVBoxLayout *layout;
    TransactionTableWidget *transactionTableWidget;
    SkuEntryController *skuEntryController;
    Ui::MainWindow *ui;
    HttpClient *httpClient;
    void initializeConnects();
    void initializeChildren();
    void initializeAndSetupLayout();
};

#endif // MAINWINDOW_H
