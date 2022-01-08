FROM gcc:latest as builder
COPY . /usr/src/receiver
WORKDIR /usr/src/receiver

RUN apt-get update && apt-get -y --no-install-recommends install \
    build-essential \
    clang \
    cmake \
    gdb
RUN cmake .; make; make install

FROM ubuntu:22.04
WORKDIR /usr/src/receiver
COPY --from=builder /usr/src/receiver .
CMD ["./receiver"]

LABEL Name=MappingEngineReceiver Version=0.0.1

# TODO: install nghttp2 with asio
# wget https://github.com/nghttp2/nghttp2/releases/download/v1.46.0/nghttp2-1.46.0.tar.bz2
# tar xf nghttp2-1.46.0.tar.bz2
# cd nghttp2-1.46.0/
# ./configure --enable-asio-lib
# sudo make install