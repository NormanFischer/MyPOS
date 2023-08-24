#include <curl/curl.h>
#include <string>
#include <nlohmann/json.hpp>

using json = nlohmann::json;

#ifndef HTTPCLIENT_H
#define HTTPCLIENT_H

struct HttpResponse {
    long statusCode;
    json body;
};

class HttpClient
{
private:
    CURL* curl;
    static size_t WriteCallback(void *contents, size_t size, size_t nmemb, void* userptr);
    static size_t header_callback(char *buffer, size_t size, size_t nitems, void *userdata);
    std::string url;
    std::string session_cookie;
    std::string userName;
public:
    enum HTTP_METHOD {GET, POST};
    HttpClient(std::string url);
    void create_curl_handle();
    void cleanup_curl();
    const bool login(const std::string &username, const std::string &password);
    const std::string getUserName();
    HttpResponse fetch(const std::string &endpoint, HTTP_METHOD method, json jsonBody=nullptr);
    ~HttpClient();
};

#endif // HTTPCLIENT_H
