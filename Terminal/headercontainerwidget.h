#ifndef HEADERCONTAINERWIDGET_H
#define HEADERCONTAINERWIDGET_H

#include <QWidget>
#include <QHBoxLayout>
#include <QLabel>
#include <item_table_row.h>
#include <core/HttpClient.h>

QT_BEGIN_NAMESPACE
QT_END_NAMESPACE

class HeaderContainerWidget : public QWidget
{
    Q_OBJECT
public:
    HeaderContainerWidget(HttpClient *httpClient, QWidget *parent);

public slots:
    void handleUserNameChangeRequest(const std::string userName);

private:
    HttpClient *httpClient;
    QHBoxLayout *layout;
    QLabel *userLabel;

};

#endif // HEADERCONTAINERWIDGET_H
