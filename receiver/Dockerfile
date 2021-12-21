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
