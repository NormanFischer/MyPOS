#include "headercontainerwidget.h"

HeaderContainerWidget::HeaderContainerWidget(HttpClient *httpClient, QWidget *parent)
    : QWidget(parent), httpClient(httpClient)
{
    userLabel = new QLabel("Logged out");
    loginStatus = new QLabel("Implement me!");
    logInLogOut = new QPushButton("Log in / Log out");
    layout = new QHBoxLayout();
    layout->addWidget(userLabel);
    layout->addWidget(loginStatus);
    layout->addWidget(logInLogOut);
    setLayout(layout);
}

void HeaderContainerWidget::handleUserNameChangeRequest(const std::string userName)
{
    userLabel->setText(QString::fromStdString(userName));
}
