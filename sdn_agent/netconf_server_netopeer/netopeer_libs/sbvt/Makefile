PKGNAME = sliceable-bvt
TARGET = sliceable-bvt.la

# Various configurable paths (remember to edit Makefile.in, not Makefile)
prefix = /usr/local
exec_prefix = ${prefix}
datarootdir = ${prefix}/share
datadir = ${datarootdir}
bindir = ${exec_prefix}/bin
includedir = ${prefix}/include
libdir =  ${exec_prefix}/lib
mandir = ${datarootdir}/man
libtool = ./libtool

CC = gcc
INSTALL = /usr/bin/install -c
INSTALL_PROGRAM = ${INSTALL}
INSTALL_DATA = ${INSTALL} -m 644
LIBS = -lxml2 -lnetconf 
CFLAGS = -I/usr/include/libxml2  -O3
CPPFLAGS = 
LDFLAGS = 
LIBTOOL = $(libtool) --tag=CC --quiet

SRCS = sliceable-bvt.c ../ext_lib/socket_writer.c
OBJDIR = .obj
LOBJS = $(SRCS:%.c=$(OBJDIR)/%.lo)

all: $(TARGET)

$(TARGET): $(LOBJS)
	$(LIBTOOL) --mode=link $(CC) $(CFLAGS) $(CPPFLAGS) $(LDFLAGS) $(LIBS) -avoid-version -module -shared -export-dynamic --mode=link -o $@ $^ -rpath $(libdir)

$(OBJDIR)/%.lo: %.c
	@[ -d $$(dirname $@) ] || \
		(mkdir -p $$(dirname $@))
	$(LIBTOOL) --mode=compile $(CC) $(CFLAGS) $(CPPFLAGS) -fPIC -shared -c $< -o $@

.PHONY: install
install: $(TARGET)
	$(INSTALL) -m 775 -d $(DESTDIR)/$(libdir)
	$(LIBTOOL) --mode=install cp $(TARGET) $(DESTDIR)/$(libdir)/;

.PHONY: clean
clean:
	$(LIBTOOL) --mode clean rm -f $(LOBJS)
	$(LIBTOOL) --mode clean rm -f $(TARGET)
	rm -rf $(TARGET) $(OBJDIR)

