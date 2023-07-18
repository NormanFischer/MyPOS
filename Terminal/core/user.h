#include "string"

#ifndef USER_H
#define USER_H
    class User {
        private:
            int userID;
            std::string nickName;
        public:
            int getUserID();
            std::string getNickName();
            void setNickName(std::string newName);
    };
#endif