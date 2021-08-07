# Cloudflare & DynDNS

Good for everyone who wants to use Cloudflare with their local ip but has a changing ip address.


## Config
```yaml
# Your cloudflare api token
apiToken: ""
# Your cloudflare email (required for auth)
cfEmail: ""
# The zoneId of the zone you want to update (Can be seen on the right of the domain dashboard)
zoneId: ""
dnsRecords:
  - name: "myendpoint.example.com"
    type: "A"
```

This project uses Cloudflare APIs only. It uses cloudflares cgi tracing endpoint to resolve the current public address and then handles changes accordingly.

## How to run
- Build with maven (java 8)
- Run on a machine like a server or a Raspberry Pi
- start once, edit configuration
- start again

## Motivation
This is a simple project used by f.e. our smart home endpoints, nginx web server etc.

Mental support provided by @iTzFreeHD