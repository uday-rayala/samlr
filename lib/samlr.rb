require "nokogiri"

module Samlr
  C14N    = Nokogiri::XML::XML_C14N_EXCLUSIVE_1_0
  COMPACT = { :indent => 0, :save_with => Nokogiri::XML::Node::SaveOptions::AS_XML }

  NS_MAP  = {
    "c14n"  => "http://www.w3.org/2001/10/xml-exc-c14n#",
    "ds"    => "http://www.w3.org/2000/09/xmldsig#",
    "saml"  => "urn:oasis:names:tc:SAML:2.0:assertion",
    "samlp" => "urn:oasis:names:tc:SAML:2.0:protocol",
    "md"    => "urn:oasis:names:tc:SAML:2.0:metadata",
    "xsi"   => "http://www.w3.org/2001/XMLSchema-instance"
  }

  EMAIL_FORMAT = "urn:oasis:names:tc:SAML:1.1:nameid-format:emailAddress"

  class SamlrError < StandardError
  end

  class FormatError < SamlrError
  end

  class SignatureError < SamlrError
  end

  class FingerprintError < SamlrError
  end

  class ConditionsError < SamlrError
  end
end

unless Object.new.respond_to?(:try)
  class Object
    def try(method)
      send(method) if respond_to?(method)
    end
  end
end

require "samlr/tools"
require "samlr/condition"
require "samlr/assertion"
require "samlr/fingerprint"
require "samlr/signature"
require "samlr/response"
require "samlr/request"
