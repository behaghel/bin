#!/usr/bin/perl -0777 -w
# -0777 means file slurping: the file passed as arg 
# will be treated as one big line.

use Text::Markdown 'markdown';
my $file = shift;
open(MYINPUT, "<$file") or die $!;
my $content = <MYINPUT>;
print markdown($content);
close(MYINPUT);
