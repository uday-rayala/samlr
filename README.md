## Samlr [![Build Status](https://secure.travis-ci.org/morten/samlr.png)](http://travis-ci.org/morten/samlr)

Samlr is a clean room implementation of SAML for Ruby.

The objective is to keep things simple and leverage Nokogiri for the heavy lifting. Samlr allows you to receive and validate SAML authentication requests. It's SAML 2.0 only, doesn't support everything and makes liberal assumptions about the input - none of which cannot be improved going forward.

### Verifying a response

You can validate a SAML response string using either of the below approaches. The fingerprint is a certificate fingerprint, and the certificate is the certificate PEM (from which Samlr will obtain the fingerprint).

```ruby
response = Samlr::Response.new(response, :fingerprint => fingerprint)
```

Or using a certificate:

```
response = Samlr::Response.new(response, :certificate => certificate)
```

You then verify the response by calling

```ruby
response.verify!
```

If the verification fails for whatever reason, a `Samlr::Error` will be thrown. This error class has several subclasses and generally contains a useful error message that can help trouble shooting.

When the verification suceeds,the resulting response object will surface `response.name_id` and `response.attributes`

### Command line

Useful to work with files, e.g.

```
$ samlr -v --skip-conditions -f 83:CC:12:...:F7:9D:19 response.xml.base64
$ Verification passed
```

Run `samlr -h` for options.

```
SAML response command line tool.

Usage examples:
  samlr --verify --fingerprint ab:23:cd --skip-conditions response.xml
  samlr --verify --skip-fingerprint --skip-conditions response.xml
  samlr --schema-validate response.xml
  samlr --print response.xml.base64
Full list of options:
            --verify, -v:   Verify a SAML response document
   --fingerprint, -f <s>:   The fingerprint to verify the certificate against
   --skip-conditions, -s:   Skip conditions check
  --skip-fingerprint, -k:   Skip certificate fingerprint check
   --schema-validate, -c:   Perform a schema validation against the input,
                            requires xmllint installed
             --print, -p:   Pretty prints the XML
              --help, -h:   Show this message
```

### Testing

```
bundle install
rake
```

### Supported IdPs

Please help adding IdP's or IdP services you find to work with Samlr

### Contributing

Pull requests very welcome. Write tests. Adhere to standards employed (indentation, spaces vs. tabs etc.).

### Error reporting

Pull requests with a failing test case much preferred.
