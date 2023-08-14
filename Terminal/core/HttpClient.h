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
public:
    HttpClient();
    void create_curl_handle();
    void cleanup_curl();
    HttpResponse fetch(std::string &url);
    ~HttpClient();
};

#endif // HTTPCLIENT_H
