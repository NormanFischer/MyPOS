#include <QDialog>
#include <QVBoxLayout>
#include <QLineEdit>
#include <QPushButton>
#include <QLabel>

#ifndef LOGINWINDOW_H
#define LOGINWINDOW_H


class LoginWindow : public QDialog
{
    Q_OBJECT
public:
    LoginWindow(QWidget *parent = nullptr);
private:
    QVBoxLayout *layout;
    QLabel *userNameLabel;
    QLineEdit *userNameEntry;
    QLabel *passwordLabel;
    QLineEdit *passwordEntry;
    QPushButton *loginButton;
signals:
    void userRequestedLogin(const std::string &userName, const std::string &password);
public slots:
    void handleLoginButtonReleased();
};

#endif // LOGINWINDOW_H
