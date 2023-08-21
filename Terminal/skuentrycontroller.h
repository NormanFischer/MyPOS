#ifndef SKUENTRYCONTROLLER_H
#define SKUENTRYCONTROLLER_H

#include <QWidget>
#include <QLineEdit>
#include <QPushButton>
#include <QHBoxLayout>
#include <item_table_row.h>
#include <core/HttpClient.h>

QT_BEGIN_NAMESPACE
QT_END_NAMESPACE

class SkuEntryController : public QWidget
{
    Q_OBJECT
public:
    SkuEntryController(HttpClient *httpClient, QWidget *parent);

private:
    QPushButton *enterSkuButton;
    QPushButton *completeTransaction;
    QLineEdit *skuEntry;
    QLineEdit *quantityEntry;
    QHBoxLayout *layout;
    HttpClient *httpClient;
public slots:
    void handleEnterSkuButtonReleased();
signals:
    void itemTableRowCreated(ItemTableRow itemTableRow);
};

#endif // SKUENTRYCONTROLLER_H
