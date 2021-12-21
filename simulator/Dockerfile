FROM gcc:latest as builder
COPY . /usr/src/simulator
WORKDIR /usr/src/simulator

RUN apt-get update && apt-get -y --no-install-recommends install \
    build-essential \
    clang \
    cmake \
    gdb
RUN cmake .; make; make install

FROM ubuntu:22.04
WORKDIR /usr/src/simulator
COPY --from=builder /usr/src/simulator .
CMD ["./simulator"]

LABEL Name=MappingEngineSimulator Version=0.0.1
