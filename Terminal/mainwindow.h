#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QPushButton>
#include <QListWidget>
#include <QVBoxLayout>
#include <QTableWidget>
#include <QLineEdit>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
private slots:
    void handleEnterSku();
private:
    QVBoxLayout *layout;
    QPushButton *enterSku;
    QTableWidget *transactionTable;
    QLineEdit *skuEntry;
    Ui::MainWindow *ui;
    int transactionRow;

};
#endif // MAINWINDOW_H