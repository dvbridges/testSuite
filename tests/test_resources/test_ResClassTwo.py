import pytest
import sys

from proj.resources.classTwo import ClassTwo

class TestClassOne():

    def setup_method(self):
        self.app = ClassTwo(1,2,3)

    def test_boolMethod(self):
        assert self.app.boolMethod()

    def teardown_method(self):
        del self.app