#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QPushButton>
#include <QListWidget>
#include <QVBoxLayout>
#include <QTableWidget>
#include <QLineEdit>
#include <core/HttpClient.h>
#include <core/transaction.h>
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
    void handleItemAdded(ItemTableRow itemTableRow);
    void processRequestedTransaction();
private:
    QVBoxLayout *layout;
    TransactionTableWidget *transactionTableWidget;
    SkuEntryController *skuEntryController;
    Ui::MainWindow *ui;
    HttpClient *httpClient;
    Transaction currentTransaction;
    void initializeConnects();
    void initializeChildren();
    void initializeAndSetupLayout();
};

#endif // MAINWINDOW_H
