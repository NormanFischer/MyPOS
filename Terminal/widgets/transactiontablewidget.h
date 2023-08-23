#ifndef TRANSACTIONTABLEWIDGET_H
#define TRANSACTIONTABLEWIDGET_H

#include <QWidget>
#include <QTableWidget>
#include <QHBoxLayout>
#include <item_table_row.h>

QT_BEGIN_NAMESPACE
QT_END_NAMESPACE

class TransactionTableWidget : public QWidget
{
    Q_OBJECT
public:
    TransactionTableWidget(QWidget *parent);
private:
    QTableWidget *transactionTable;
    QHBoxLayout *layout;
    void populateTableRow(ItemTableRow itemTableRow);
public slots:
    void onItemReceived(ItemTableRow itemTableRow);
};

#endif // TRANSACTIONTABLEWIDGET_H
