#include "headercontainerwidget.h"

HeaderContainerWidget::HeaderContainerWidget(HttpClient *httpClient, QWidget *parent)
    : QWidget(parent), httpClient(httpClient)
{
    userLabel = new QLabel("Logged out");
    layout = new QHBoxLayout();
    layout->addWidget(userLabel);
    setLayout(layout);
}

void HeaderContainerWidget::handleUserNameChangeRequest(const std::string userName)
{
    userLabel->setText(QString::fromStdString(userName));
}
