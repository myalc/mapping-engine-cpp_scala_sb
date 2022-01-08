## Use cases

Use links to encode/decode base64 strings: 
* https://www.base64encode.org/
* https://www.base64decode.org/

Sample source data 1
```json
{
   "myFieldA":1,
   "myFieldB":"g",
   "t":{
      "a":"aaa",
      "b":"bbb"
   },
   "ta":[
      {
         "c":"aaa0",
         "d":"bbb0",
         "k": {
           "ka": 0,
           "kb": "my0",
           "aa": ["aa0", "bb0", "cc0"]
         }
      },
      {
         "c":"aaa1",
         "d":"bbb1",
         "k": {
            "ka": 1,
            "kb": "my1",
            "aa": ["aa1", "bb1", "cc1"]
         }
      }
   ],
   "ar1": ["aq", "er", "ty" ],
   "ar2": [1, 3, 5, 7, 99],
   "ar3": [
        {"attr1": "val0", "attr2": 0},
        {"attr1": "val1", "attr2": 1},
        {"attr1": "val2", "attr2": 2}
   ],
   "ar4": [
           {"attr1": "val10", "attr2": 10},
           {"attr1": "val11", "attr2": 11},
           {"attr1": "val12", "attr2": 12}
      ]
}
```

```json
config1.json mapping
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "a": "{{t/a}}",
    "b": "{{t/b}}"
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAiYSI6ICJ7e3QvYX19IiwKICAgICJiIjogInt7dC9ifX0iCn0=
```

```json
config2.json mapping
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta": "{{t/a}}",
    "tb": "{{t/b}}",
    "ta0c": "{{ta/*0/c}}",
    "ta0d": "{{ta/*0/d}}"
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEiOiAie3t0L2F9fSIsCiAgICAidGIiOiAie3t0L2J9fSIsCiAgICAidGEwYyI6ICJ7e3RhLyowL2N9fSIsCiAgICAidGEwZCI6ICJ7e3RhLyowL2R9fSIKfQ==
```

```sh
config3.json mapping:
{
    "myFA": {{myFieldA}},
    "myFB": {{myFieldB}},
    "ta0": {
        "c": {{ta/*0/c}},
        "d": {{ta/*0/d}}
    },
    "ta1": {
        "c": {{ta/*1/c}},
        "d": {{ta/*1/d}}
    }
}
base64: ewogICAgIm15RkEiOiB7e215RmllbGRBfX0sCiAgICAibXlGQiI6IHt7bXlGaWVsZEJ9fSwKICAgICJ0YTAiOiB7CiAgICAgICAgImMiOiB7e3RhLyowL2N9fSwKICAgICAgICAiZCI6IHt7dGEvKjAvZH19CiAgICB9LAogICAgInRhMSI6IHsKICAgICAgICAiYyI6IHt7dGEvKjEvY319LAogICAgICAgICJkIjoge3t0YS8qMS9kfX0KICAgIH0KfQ==
```

```sh
config4.json mapping:
{
    "myFA": {{myFieldA}},
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}}
}
base64: ewogICAgIm15RkEiOiB7e215RmllbGRBfX0sCiAgICAibXlGQiI6ICJ7e215RmllbGRCfX0iLAogICAgInRhMCI6IHsKICAgICAgICAiYSI6ICJ7e3RhLyowL2N9fSIsCiAgICAgICAgImIiOiAie3t0YS8qMC9kfX0iLAogICAgICAgICJkeW1teSI6ICJkdW1teSIKICAgIH0sCiAgICAidGExIjoge3t0YS8qMX19Cn0=
```

```sh
config5.json mapping:
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "myar1": {{ar1}},
    "myar2": {{ar2}}
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAibXlhcjEiOiB7e2FyMX19LAogICAgIm15YXIyIjoge3thcjJ9fQp9
```

```sh
config6.json mapping:
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "myar1": {{ar1}},
    "myar2": {{ar2}},
    "myar3": {{ar3}}
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAibXlhcjEiOiB7e2FyMX19LAogICAgIm15YXIyIjoge3thcjJ9fSwKICAgICJteWFyMyI6IHt7YXIzfX0KfQ==
```

```
config7.json
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "myar1": {{ar1}},
    "myar2": {{ar2}},
    "myar3": {{ar3}},
    "myObj1":
        {{#ar4}}
        {
            "aa": "{{attr1}}",
            "cc": {{attr2}}
        }
        {{/ar4}}
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAibXlhcjEiOiB7e2FyMX19LAogICAgIm15YXIyIjoge3thcjJ9fSwKICAgICJteWFyMyI6IHt7YXIzfX0sCiAgICAibXlPYmoxIjoKICAgICAgICB7eyNhcjR9fQogICAgICAgIHsKICAgICAgICAgICAgImFhIjogInt7YXR0cjF9fSIsCiAgICAgICAgICAgICJjYyI6IHt7YXR0cjJ9fQogICAgICAgIH0KICAgICAgICB7ey9hcjR9fQp9
```

Handle multiple not nested loops in mapping 
```
config8.json
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "myar1": {{ar1}},
    "myar2": {{ar2}},
    "myar3": {{ar3}},
    "myObj1":
        {{#ar4}}
        {
            "aa": "{{attr1}}",
            "cc": {{attr2}}
        }
        {{/ar4}},
    "myObj2":
        {{#ar3}}
        {
            "xx": {{attr2}},
            "yy": "{{attr1}}"
        }
        {{/ar3}}
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAibXlhcjEiOiB7e2FyMX19LAogICAgIm15YXIyIjoge3thcjJ9fSwKICAgICJteWFyMyI6IHt7YXIzfX0sCiAgICAibXlPYmoxIjoKICAgICAgICB7eyNhcjR9fQogICAgICAgIHsKICAgICAgICAgICAgImFhIjogInt7YXR0cjF9fSIsCiAgICAgICAgICAgICJjYyI6IHt7YXR0cjJ9fQogICAgICAgIH0KICAgICAgICB7ey9hcjR9fSwKICAgICJteU9iajIiOgogICAgICAgIHt7I2FyM319CiAgICAgICAgewogICAgICAgICAgICAieHgiOiB7e2F0dHIyfX0sCiAgICAgICAgICAgICJ5eSI6ICJ7e2F0dHIxfX0iCiAgICAgICAgfQogICAgICAgIHt7L2FyM319Cn0=
```

Handle custom functions like datetime format converter, trim, etc
```sh
config9.json
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "fnc1": "{{#func_wrapped}}{{myFieldB}} is awesome.{{/func_wrapped}}"
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAiZm5jMSI6ICJ7eyNmdW5jX3dyYXBwZWR9fXt7bXlGaWVsZEJ9fSBpcyBhd2Vzb21lLnt7L2Z1bmNfd3JhcHBlZH19Igp9
```

```sh
config91.json
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "fnc1": "{{#func_wrapped}}{{myFieldB}} is awesome.{{/func_wrapped}}",
    "fnc2": "{{#func_wrapped2}}{{myFieldB}}|d|{{ta/*0/c}}|d|{{myFieldA}}{{/func_wrapped2}}"
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAiZm5jMSI6ICJ7eyNmdW5jX3dyYXBwZWR9fXt7bXlGaWVsZEJ9fSBpcyBhd2Vzb21lLnt7L2Z1bmNfd3JhcHBlZH19IiwKICAgICJmbmMyIjogInt7I2Z1bmNfd3JhcHBlZDJ9fXt7bXlGaWVsZEJ9fXxkfHt7dGEvKjAvY319fGR8e3tteUZpZWxkQX19e3svZnVuY193cmFwcGVkMn19Igp9
```

```sh
config10:
{
    "myFA": "{{myFieldA}}",
    "myFB": "{{myFieldB}}",
    "ta0": {
        "a": "{{ta/*0/c}}",
        "b": "{{ta/*0/d}}",
        "dymmy": "dummy"
    },
    "ta1": {{ta/*1}},
    "myar1": {{ar1}},
    "myar2": {{ar2}},
    "myar3": {{ar3}},
    "myObj1":
        {{#ar4}}
        {
            "aa": "{{attr1}}",
            "cc": {{attr2}}
        }
        {{/ar4}},
    "myObj2":
        {{#ar3}}
        {
            "xx": {{attr2}},
            "yy": "{{attr1}}"
        }
        {{/ar3}},
    "fnc1": "{{#func_wrapped}}{{myFieldB}} is awesome.{{/func_wrapped}}",
    "fnc2": "{{#func_wrapped2}}{{myFieldB}}|d|{{ta/*0/c}}|d|{{myFieldA}}{{/func_wrapped2}}"
}
base64: ewogICAgIm15RkEiOiAie3tteUZpZWxkQX19IiwKICAgICJteUZCIjogInt7bXlGaWVsZEJ9fSIsCiAgICAidGEwIjogewogICAgICAgICJhIjogInt7dGEvKjAvY319IiwKICAgICAgICAiYiI6ICJ7e3RhLyowL2R9fSIsCiAgICAgICAgImR5bW15IjogImR1bW15IgogICAgfSwKICAgICJ0YTEiOiB7e3RhLyoxfX0sCiAgICAibXlhcjEiOiB7e2FyMX19LAogICAgIm15YXIyIjoge3thcjJ9fSwKICAgICJteWFyMyI6IHt7YXIzfX0sCiAgICAibXlPYmoxIjoKICAgICAgICB7eyNhcjR9fQogICAgICAgIHsKICAgICAgICAgICAgImFhIjogInt7YXR0cjF9fSIsCiAgICAgICAgICAgICJjYyI6IHt7YXR0cjJ9fQogICAgICAgIH0KICAgICAgICB7ey9hcjR9fSwKICAgICJteU9iajIiOgogICAgICAgIHt7I2FyM319CiAgICAgICAgewogICAgICAgICAgICAieHgiOiB7e2F0dHIyfX0sCiAgICAgICAgICAgICJ5eSI6ICJ7e2F0dHIxfX0iCiAgICAgICAgfQogICAgICAgIHt7L2FyM319LAogICAgImZuYzEiOiAie3sjZnVuY193cmFwcGVkfX17e215RmllbGRCfX0gaXMgYXdlc29tZS57ey9mdW5jX3dyYXBwZWR9fSIsCiAgICAiZm5jMiI6ICJ7eyNmdW5jX3dyYXBwZWQyfX17e215RmllbGRCfX18ZHx7e3RhLyowL2N9fXxkfHt7bXlGaWVsZEF9fXt7L2Z1bmNfd3JhcHBlZDJ9fSIKfQ==
```


Test hello world API
```sh
curl --location -ki --request GET 'http://localhost:9000/mappingengine/hello/world' --header 'Content-Type: text/plain'
```