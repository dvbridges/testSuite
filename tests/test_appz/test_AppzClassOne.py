import pytest
import sys

from proj.appz.classOne import ClassOne

class TestClassOne():

    def setup_method(self):
        self.app = ClassOne(1,2,3)

    def test_boolMethod(self):
        assert self.app.boolMethod()

    def teardown_method(self):
        del self.app
