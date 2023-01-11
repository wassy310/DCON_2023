import dataclasses

from collections import namedtuple
from typing import List
from typing import Tuple

Bndbox = namedtuple('Bndbox', ('xmin', 'ymin', 'xmax', 'ymax'))
Size = namedtuple('Size', ('width', 'height', 'depth'))


@dataclasses.dataclass
class VocXmlDataset(object):
    size: Size
    objects: List[Tuple[str, Bndbox]] = lambda: []
