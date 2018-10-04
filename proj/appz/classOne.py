#!usr/bin/env python
# -*- coding: utf8 -*-

class ClassOne(object):
    """Class docstring"""
    def __init__(self, a, b, c):

        self.a = a
        self.b = b
        self.c = c

    def boolMethod(self):
        return True

    def untestedMethod(self):
        return True