#include "loginwindow.h"
#include "QScreen"

LoginWindow::LoginWindow(QWidget *parent)
    : QDialog(parent)
{
    userNameLabel = new QLabel("Username:", this);
    userNameEntry = new QLineEdit(this);
    passwordLabel = new QLabel("Password:", this);
    passwordEntry = new QLineEdit(this);
    loginButton = new QPushButton("Login", this);

    layout = new QVBoxLayout(this);

    layout->addWidget(userNameLabel);
    layout->addWidget(userNameEntry);
    layout->addWidget(passwordLabel);
    layout->addWidget(passwordEntry);
    layout->addWidget(loginButton);

    setLayout(layout);
}

void LoginWindow::handleLoginButtonReleased()
{
    emit userRequestedLogin(userNameEntry->text().toUtf8().constData(),
                            passwordEntry->text().toUtf8().constData());
}
