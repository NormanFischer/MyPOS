#include <curl/curl.h>
#include <string>
#ifndef HTTPCLIENT_H
#define HTTPCLIENT_H

struct HttpResponse {
    long statusCode;
    std::string body;
};

class HttpClient
{
private:
    CURL* curl;
    static size_t WriteCallback(void* contents, size_t size, size_t nmemb, void* userptr);
    std::string url;
public:
    enum HTTP_METHOD {GET, POST};
    HttpClient(std::string url);
    void create_curl_handle();
    void cleanup_curl();
    std::string login(std::string username, std::string password);
    HttpResponse fetch(const std::string &endpoint, HTTP_METHOD method, std::string jsonBody="");
    static size_t header_callback(char *buffer, size_t size, size_t nitems, void *userdata);
    ~HttpClient();
};

#endif // HTTPCLIENT_H
