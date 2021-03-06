# Copyright (C) 2014 Yen-Chin, Lee <coldnew.tw@gmail.com>

# A blacklisted recipe is ignored by bitbake. The reason it is useful to be able
# to have bitbake ignore a recipe is because, although PREFERRED_PROVIDER can be
# used to select which recipe is used to fulfill a build-time dependency, bitbake
# does not have a PREFERRED_RPROVIDER to allow specification of which package
# should be used to satisfy a run-time dependency. When two recipes both provide
# common packages, but one recipe provides additional packages that the other
# doesn't, then the only way to guarantee that the first recipe will never be
# used is to blacklist it.

INHERIT += "blacklist"

PNBLACKLIST[gst-plugins-bad] = "use gstreamer1.0-plugins instead"
PNBLACKLIST[gst-plugins-base] = "use gstreamer1.0-plugins instead"
PNBLACKLIST[gst-plugins-good] = "use gstreamer1.0-plugins instead"
PNBLACKLIST[gst-plugins-ugly] = "use gstreamer1.0-plugins instead"
PNBLACKLIST[gstreamer] = "use gstreamer1.0-plugins instead"

PNBLACKLIST[libglu] = "broken: we don't build libGL with mesa: cannot find -lGL"

# Don't build any fragment opensource packages
PNBLACKLIST[mir] = "use wayland instead"
PNBLACKLIST[qtmir] = "use wayland instead"
PNBLACKLIST[qtubuntu] = "use wayland instead"
